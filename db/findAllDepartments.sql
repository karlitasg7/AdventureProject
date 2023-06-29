CREATE OR ALTER PROCEDURE HumanResources.findAllDepartments
    AS
BEGIN
    SET NOCOUNT ON;

SELECT DepartmentID, Name
FROM HumanResources.Department
ORDER BY Name;

END
