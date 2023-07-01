CREATE OR ALTER PROCEDURE HumanResources.deleteEmployee @BusinessEntityID INT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE HumanResources.Employee
    SET CurrentFlag  = 0
      , ModifiedDate = GETDATE()
    WHERE BusinessEntityID = @BusinessEntityID;
END
