SELECT category, SUM(sales) AS total_sales
FROM book
JOIN book_sales USING (book_id)
# WHERE YEAR(sales_date) = 2022 AND MONTH(sales_date) = 1
WHERE SUBSTR(sales_date, 1, 7) = '2022-01'
GROUP BY category
ORDER BY category ASC