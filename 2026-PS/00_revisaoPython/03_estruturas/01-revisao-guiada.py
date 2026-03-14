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
titulos = (
    "O Programador Pragmatico",
    "Código limpo",
    "Entendendo Algoritmos",
)

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
print ("Status:", "Disponível" if livro["disponivel"] else "Indisponível")

