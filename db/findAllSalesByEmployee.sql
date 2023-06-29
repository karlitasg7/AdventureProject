CREATE OR ALTER PROCEDURE HumanResources.findAllSalesByEmployee @BusinessEmployeeId INT,
    @StartDate DATE,
    @EndDate DATE
    AS
BEGIN
    SET NOCOUNT ON;

SELECT t0.OrderDate
     , CASE
           WHEN t0.Status = 1 THEN 'In process'
           WHEN t0.Status = 2 THEN 'Approved'
           WHEN t0.Status = 3 THEN 'Back Ordered'
           WHEN t0.Status = 4 THEN 'Rejected'
           WHEN t0.Status = 5 THEN 'Shipped'
           WHEN t0.Status = 6 THEN 'Cancelled'
    END                                                                                      AS statusName
     , t0.AccountNumber
     , CONCAT(t1.AddressLine1, ' ', t1.City, ' ', t2.Name, ' ', t1.PostalCode, ' ', t3.Name) AS address
     , t0.SubTotal
     , t0.TaxAmt
     , t0.SubTotal + t0.TaxAmt                                                               AS total
FROM Sales.SalesOrderHeader        t0
         JOIN Person.Address       t1
              ON t0.BillToAddressID = t1.AddressID
         JOIN Person.StateProvince t2
              ON t1.StateProvinceID = t2.StateProvinceID
         JOIN Person.CountryRegion t3
              ON t2.CountryRegionCode = t3.CountryRegionCode
WHERE t0.CurrencyRateID IS NULL -- necessary to show only sales from US in $
  AND t0.OrderDate BETWEEN @StartDate AND @EndDate
  AND t0.SalesPersonID = @BusinessEmployeeId

END
