CREATE OR ALTER PROCEDURE HumanResources.insertEmployee @FirstName VARCHAR(50),
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

    DECLARE @BusinessEntityID INT;
    DECLARE @AddressID INT;

    INSERT INTO Person.BusinessEntity (rowguid, ModifiedDate)
    VALUES (NEWID(), GETDATE());

    SET @BusinessEntityID = SCOPE_IDENTITY();

    INSERT INTO Person.Person
    (BusinessEntityID, PersonType, FirstName, MiddleName, LastName, ModifiedDate)
    VALUES (@BusinessEntityID, 'EM', @FirstName, @MiddleName, @LastName, GETDATE());

    INSERT INTO HumanResources.Employee
    ( BusinessEntityID, NationalIDNumber, LoginID, JobTitle, BirthDate, MaritalStatus, Gender, HireDate, CurrentFlag
    , ModifiedDate)
    VALUES ( @BusinessEntityID, @BusinessEntityID, @BusinessEntityID, @JobTitle, @BirthDay, 'S', 'F', GETDATE(), 1
           , GETDATE());

    INSERT INTO Person.PersonPhone
    (BusinessEntityID, PhoneNumber, PhoneNumberTypeID, ModifiedDate)
    VALUES (@BusinessEntityID, @PhoneNumber, 1, GETDATE());

    INSERT INTO Person.EmailAddress
    (BusinessEntityID, EmailAddress, ModifiedDate)
    VALUES (@BusinessEntityID, @EmailAddress, GETDATE());

    INSERT INTO Person.Address
    (AddressLine1, AddressLine2, City, StateProvinceID, PostalCode, ModifiedDate)
    VALUES (@AddressLine1, @AddressLine2, @City, @Province, @PostalCode, GETDATE());

    SET @AddressID = SCOPE_IDENTITY();

    INSERT INTO Person.BusinessEntityAddress
    (BusinessEntityID, AddressID, AddressTypeID, ModifiedDate)
    VALUES (@BusinessEntityID, @AddressID, 2, GETDATE());

    EXEC HumanResources.insertEmployeeHistory @BusinessEntityID,
             @DepartmentId,
             @ShiftID,
             @StartDate

    SELECT @BusinessEntityID AS NewBusinessEntityID;
END
