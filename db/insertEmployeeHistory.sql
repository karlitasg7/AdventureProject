CREATE OR ALTER PROCEDURE HumanResources.insertEmployeeHistory @BusinessEntityID INT,
    @DepartmentId INT,
    @ShiftID INT,
    @StartDate DATE
    AS
BEGIN
    SET NOCOUNT ON;

    UPDATE HumanResources.EmployeeDepartmentHistory
    SET EndDate = DATEADD(DD, -1, @StartDate)
    WHERE BusinessEntityID = @BusinessEntityID
      AND EndDate IS NULL;

    INSERT INTO HumanResources.EmployeeDepartmentHistory
    (BusinessEntityID, DepartmentID, ShiftID, StartDate, ModifiedDate)
    VALUES (@BusinessEntityID, @DepartmentId, @ShiftID, @StartDate, GETDATE());

END
