CREATE OR ALTER PROCEDURE HumanResources.updateEmployee @BusinessEntityID INT,
    @FirstName VARCHAR(50),
    @MiddleName VARCHAR(50),
    @LastName VARCHAR(50),
    @JobTitle VARCHAR(50),
    @BirthDay DATE,
    @PhoneNumber VARCHAR(20),
    @EmailAddress VARCHAR(50),
    @AddressLine1 VARCHAR(200),
    @AddressLine2 VARCHAR(200),
    @City VARCHAR(200),
    @PostalCode VARCHAR(200),
    @Province INT,
    @DepartmentId INT,
    @ShiftID INT,
    @StartDate DATE
    AS
BEGIN
    SET NOCOUNT ON;

UPDATE Person.Person
SET FirstName    = @FirstName
  , MiddleName   = @MiddleName
  , LastName     = @LastName
  , ModifiedDate = GETDATE()
WHERE BusinessEntityID = @BusinessEntityID;

UPDATE HumanResources.Employee
SET BirthDate    = @BirthDay
  , ModifiedDate = GETDATE()
WHERE BusinessEntityID = @BusinessEntityID;

UPDATE Person.PersonPhone
SET PhoneNumber  = @PhoneNumber
  , ModifiedDate = GETDATE()
WHERE BusinessEntityID = @BusinessEntityID;

UPDATE Person.EmailAddress
SET EmailAddress = @EmailAddress
  , ModifiedDate = GETDATE()
WHERE BusinessEntityID = @BusinessEntityID;

UPDATE Person.Address
SET AddressLine1    = @AddressLine1
  , AddressLine2    = @AddressLine2
  , City            = @City
  , StateProvinceID = @Province
  , PostalCode      = @PostalCode
  , ModifiedDate    = GETDATE()
WHERE AddressID = (SELECT BusinessEntityAddress.AddressID
                   FROM Person.BusinessEntityAddress
                   WHERE BusinessEntityID = @BusinessEntityID
                     AND AddressTypeID = 2);

IF @DepartmentId > 0
BEGIN

UPDATE HumanResources.Employee
SET JobTitle = @JobTitle
WHERE BusinessEntityID = @BusinessEntityID;

EXEC HumanResources.insertEmployeeHistory @BusinessEntityID,
                 @DepartmentId,
                 @ShiftID,
                 @StartDate

END

END
