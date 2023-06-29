CREATE OR ALTER PROCEDURE HumanResources.findAllProvince
    AS
BEGIN
    SET NOCOUNT ON;

SELECT StateProvinceID, Name
FROM Person.StateProvince
ORDER BY Name;

END
