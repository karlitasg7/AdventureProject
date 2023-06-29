CREATE OR ALTER PROCEDURE HumanResources.findAll
    AS
BEGIN
    SET NOCOUNT ON;

SELECT t0.BusinessEntityID
     , CONCAT(t1.FirstName, t1.MiddleName, t1.LastName) AS name
     , t0.JobTitle
     , t3.DepartmentID
     , t3.Name                                          AS department
     , t2.StartDate
     , t0.BirthDate
     , t4.PhoneNumber
     , t5.EmailAddress
     , CONCAT(t7.AddressLine1, ' ', t7.AddressLine2, ' ', t7.City, ' ', t8.Name, ' ', t7.PostalCode, ' ',
              t9.Name)                                  AS address
FROM HumanResources.Employee                           t0
         JOIN Person.Person                            t1
              ON t0.BusinessEntityID = t1.BusinessEntityID
         JOIN HumanResources.EmployeeDepartmentHistory t2
              ON t0.BusinessEntityID = t2.BusinessEntityID
         JOIN (SELECT BusinessEntityID
                    , MAX(StartDate) AS MaxStartDate
               FROM HumanResources.EmployeeDepartmentHistory
               GROUP BY BusinessEntityID) AS           maxdh
              ON t2.BusinessEntityID = maxdh.BusinessEntityID
                  AND t2.StartDate = maxdh.MaxStartDate
         JOIN HumanResources.Department                t3
              ON t2.DepartmentID = t3.DepartmentID
         JOIN Person.PersonPhone                       t4
              ON t1.BusinessEntityID = t4.BusinessEntityID
         JOIN Person.EmailAddress                      t5
              ON t1.BusinessEntityID = t5.BusinessEntityID
         JOIN Person.BusinessEntityAddress             t6
              ON t6.BusinessEntityID = t0.BusinessEntityID
                  AND t6.AddressTypeID = 2 -- Home Address
         JOIN Person.Address                           t7
              ON t6.AddressID = t7.AddressID
         JOIN Person.StateProvince                     t8
              ON t7.StateProvinceID = t8.StateProvinceID
         JOIN Person.CountryRegion                     t9
              ON t8.CountryRegionCode = t9.CountryRegionCode
WHERE t0.CurrentFlag = 1;

END
