# Quais as unidades da federação cadastradas na base?
SELECT DISTINCT uf FROM oficina.cidades;

# 1 - Quais os nomes clientes do estado do RS?
SELECT cl.nome FROM oficina.clientes cl
INNER JOIN oficina.cidades cd ON (cd.id = cl.cidades_id)
WHERE cd.uf = 'RS';

SELECT cl.nome
FROM oficina.clientes cl, oficina.cidades cd
WHERE cd.id = cl.cidades_id AND cd.uf = 'RS';

# 2 - Quantos são os clientes do estado do RJ?
# Exemplos de funções do SQL: count(*), max(campo), min(campo), avg(campo)...

SELECT count(*) FROM oficina.clientes cl
INNER JOIN oficina.cidades cd ON (cd.id = cl.cidades_id)
WHERE cd.uf = 'RJ';

# Qual o veículo mais antigo?
SELECT * FROM oficina.veiculos v
WHERE v.ano = (select min(ano) from oficina.veiculos v);

# Retorna o menor ano de fabricação: select min(ano) from oficina.veiculos v;

# 3 - Quais as cidades e nomes dos cliente do MA?
SELECT cl.nome, cd.nome FROM oficina.clientes cl
INNER JOIN oficina.cidades cd ON (cd.id = cl.cidades_id)
WHERE cd.uf = 'MA';

# 4 - Quais os tipos dos veículos cadastrados na base que são da Honda?
SELECT m.descricao, t.descricao FROM oficina.veiculos v
INNER JOIN oficina.marcas m ON (m.id = v.marcas_id)
INNER JOIN oficina.tipos_veiculos t ON (t.id = v.tipos_veiculos_id)
WHERE m.descricao = 'Honda';

# 5 - Quantos veículos existem catastrados na base agrupados por marca? Ou seja, quantos veículos de cada marca?
SELECT m.descricao, count(*) AS total FROM oficina.veiculos v
INNER JOIN oficina.marcas m ON (m.id = v.marcas_id)
GROUP BY m.id;

# 5.1 - Quantos veículos existem catastrados na base agrupados por marca?
# Ou seja, quantos veículos de cada marca? Considerar marcas com mais de 30 veículos cadastrados
SELECT m.descricao, count(*) AS total FROM oficina.veiculos v
INNER JOIN oficina.marcas m ON (m.id = v.marcas_id)
GROUP BY m.id
HAVING total >= 30;

# 6 - Quantos veículos de cada tipo?
SELECT t.descricao, count(*) AS total FROM oficina.veiculos v
INNER JOIN oficina.tipos_veiculos t ON (t.id = v.tipos_veiculos_id)
GROUP BY t.id;

# 7 - Quantos veiculos cada cliente tem? (considerar apenas os que tem mais de 1)
SELECT c.nome, count(*) as num_registros
FROM oficina.clientes c 
INNER JOIN oficina.veiculos v ON (c.id = v.clientes_id)
GROUP BY c.id;

# 8 - Quais os clientes que não tem carro?
SELECT c.nome
FROM oficina.clientes c 
LEFT OUTER JOIN oficina.veiculos v ON (c.id = v.clientes_id)
WHERE v.id IS NULL;