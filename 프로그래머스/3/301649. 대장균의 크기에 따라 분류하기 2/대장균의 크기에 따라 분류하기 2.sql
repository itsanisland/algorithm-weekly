SELECT ID,
(
    CASE
        WHEN RK / TOTAL <= 0.25 THEN 'CRITICAL'
        WHEN RK / TOTAL <= 0.5 THEN 'HIGH'
        WHEN RK / TOTAL <= 0.75 THEN 'MEDIUM'
        ELSE 'LOW'
    END
) AS COLONY_NAME
FROM (
    SELECT ID, 
        ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) AS RK,
        COUNT(*) OVER () AS TOTAL # 전체 행 개수를 각 행에 붙임
    FROM ECOLI_DATA
) T
ORDER BY 1


# SELECT ID,
#         CASE
#             WHEN RN / TOTAL <= 0.25 THEN 'CRITICAL'
#             WHEN RN / TOTAL <= 0.50 THEN 'HIGH'
#             WHEN RN / TOTAL <= 0.75 THEN 'MEDIUM'
#             ELSE 'LOW'
#         END COLONY_NAME
# FROM (
#     SELECT ID,
#             ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) RN,
#             COUNT(*) OVER() TOTAL
#     FROM ECOLI_DATA
# ) t
# ORDER BY ID;