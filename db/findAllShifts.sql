CREATE OR ALTER PROCEDURE HumanResources.findAllShifts
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ShiftID, Name
    FROM HumanResources.Shift
    ORDER BY Name;

END
