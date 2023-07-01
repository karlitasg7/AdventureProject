CREATE OR ALTER PROCEDURE HumanResources.existById @BusinessEntityID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT COUNT(BusinessEntityID)
    FROM HumanResources.Employee
    WHERE BusinessEntityID = @BusinessEntityID;

END
