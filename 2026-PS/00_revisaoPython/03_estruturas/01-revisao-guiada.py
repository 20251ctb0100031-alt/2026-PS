# ==========================================
# SISTEMA DE CONVERSÃO DE UNIDADES
# ===========================================  
# Disciplina : Programação de Sistemas (PS)
# Aula       : 05 - Revisao: Estrutura de dados
# Autor      : Fernando
# Data       : 05/03/2026
# Repositorio:  https://github.com/20251ctb0100031-alt/2026-PS
# ============================================

# ---- LISTAS: CONCEITOS BASICOS ----
#Criando uma lista de titulos
titulos = [
    "O Programador Pragmatico",
    "Código limpo",
    "Entendendo Algoritmos",
]

# Acesso pro indice (cemeça em 0, não em 1)
print ("Primeiro Livro:", titulos[0])
print ("Otimo Livro:", titulos[-1])
print ("Total Livro:", len(titulos))

#----MÈTODO DE Listas-------
print("\n--- Operadores na lista ----")

titulos.append("Python Fluente")
print("Após append:", titulos)

busca = "Código Limpo"
if busca in titulos:
    print(f"'{busca}' esta no catalogo")
else:
    print(f"'{busca}' não encontrado")

    titulos.sort()
    print("Lista ordenado:", titulos)

titulos.remove("Entendendo Algoritmos")
print ("Após remove:", titulos)

#----- DICIONÁRIOS: CONCEITOS BÁSICOS ----

#um dicionario reprenta um livro com seus atributos
livro = {
    "titulo": "O Programador Pragmatico",
    "autor": "Andrew Hunt",
    "ano": 1999,
    "disponivel": True,
}

print ("Titulo:", livro["titulo"])
print ("Autor:", livro["autor"])
print ("Ano:", livro["ano"])
print ("Status:", "Disponível" if livro["disponivel"] else "Emprestado")

# ---- MODIFICANDO E CONSULTANDO ----

# Atualizando um valor existente
livro["disponivel"] = False    # livro foi emprestado
print("\nApós empréstimo:", livro["disponivel"])

# Adicionando uma nova chave
livro["paginas"] = 352
print("Páginas:", livro["paginas"])

# .get() — acesso seguro: retorna None (ou padrão) se a chave não existir
editora = livro.get("editora", "Não informada")
print("Editora:", editora)    # não lança KeyError, retorna o padrão

# ---- CATÁLOGO: LISTA DE DICIONÁRIOS ----

catalogo = [
    {"titulo": "O Programador Pragmático", "autor": "Andrew Hunt", "ano": 1999, "disponivel": True},
    {"titulo": "Código Limpo", "autor": "Robert C. Martin", "ano": 2008, "disponivel": False},
    {"titulo": "Entendendo Algoritmos", "autor": "Aditya Bhargava", "ano": 2016, "disponivel": True},
]

print("=== Catálogo da Biblioteca ===")
print()

# Percorrendo cada livro com for
for livro in catalogo:
    status = "✅ Disponível" if livro["disponivel"] else "📕 Emprestado"
    print(f' {livro["titulo"]} ({livro["ano"]})')
    print(f' Autor: {livro["autor"]} | {status}')
    print(" " + "-" * 40)

# ---- CONSULTAS E FILTROS ----

print("\n=== Livros disponíveis ===")
for livro in catalogo:
    if livro["disponivel"]:                         # filtra apenas os disponíveis
        print(f' ✅ {livro["titulo"]}')

print("\n=== Busca por título ===")
busca = input("Digite o título (ou parte): ").lower()
encontrado = False
for livro in catalogo:
    if busca in livro["titulo"].lower():            # .lower() ignora maiúsculas/minúsculas
        print(f' Encontrado: {livro["titulo"]} – {livro["autor"]}')
        encontrado = True
if not encontrado:
    print(" Nenhum livro encontrado com esse termo.")

print("\n=== Atributos do primeiro livro ===")
for chave, valor in catalogo[0].items():            # .items() retorna pares (chave, valor)
    print(f" {chave}: {valor}")
