import datetime

# ============================================================
# Nome: Fernando
# Disciplina: Programação de sistemas
# Data: 14/03/2026
# Descrição: Sistema de Catálogo de Livros com cadastro, busca,
#            empréstimo, devolução e relatório final.
# ============================================================


# ---------------------- Dados Iniciais ----------------------
catalogo = [
    {"titulo": "Dom Casmurro", "autor": "Machado de Assis", "ano": 1899, "disponivel": True},
    {"titulo": "O Pequeno Príncipe", "autor": "Antoine de Saint-Exupéry", "ano": 1943, "disponivel": False},
    {"titulo": "1984", "autor": "George Orwell", "ano": 1949, "disponivel": True}
]

# ---------------------- Funções Utilitárias -----------------
def exibir_catalogo(catalogo):
    print("\nCatálogo de Livros:")
    for livro in catalogo:
        status = "Disponível" if livro["disponivel"] else "Emprestado"
        print(f"- {livro['titulo']} | {livro['autor']} | {status}")

def cadastrar_livro(catalogo):
    print("\nCadastro de novo livro:")
    titulo = input("Título: ").strip()
    autor = input("Autor: ").strip()
    ano = input("Ano: ").strip()
    if not ano.isdigit():
        print("Ano inválido. Cadastro cancelado.")
        return
    catalogo.append({
        "titulo": titulo,
        "autor": autor,
        "ano": int(ano),
        "disponivel": True
    })
    print("Livro cadastrado com sucesso!")

def buscar_por_autor(catalogo):
    termo = input("\nDigite o nome (ou parte) do autor para buscar: ").strip().lower()
    encontrados = [livro for livro in catalogo if termo in livro["autor"].lower()]
    if encontrados:
        print("\nLivros encontrados:")
        for livro in encontrados:
            status = "Disponível" if livro["disponivel"] else "Emprestado"
            print(f"- {livro['titulo']} | {livro['autor']} | {status}")
    else:
        print("Nenhum livro encontrado para esse autor.")

def contagem_livros(catalogo):
    total = len(catalogo)
    disponiveis = sum(1 for livro in catalogo if livro["disponivel"])
    emprestados = total - disponiveis
    print(f"\nTotal de livros: {total}")
    print(f"Disponíveis: {disponiveis}")
    print(f"Emprestados: {emprestados}")

def alternar_emprestimo(catalogo):
    titulo = input("\nDigite o título do livro para emprestar/devolver: ").strip().lower()
    for livro in catalogo:
        if livro["titulo"].lower() == titulo:
            livro["disponivel"] = not livro["disponivel"]
            acao = "devolvido" if livro["disponivel"] else "emprestado"
            print(f"Livro '{livro['titulo']}' {acao} com sucesso!")
            return
    print("Livro não encontrado no catálogo.")

def relatorio_final(catalogo):
    total = len(catalogo)
    disponiveis = sum(1 for livro in catalogo if livro["disponivel"])
    emprestados = total - disponiveis
    emprestados_titulos = [livro["titulo"] for livro in catalogo if not livro["disponivel"]]
    print("\n===== RELATÓRIO FINAL =====")
    print(f"Total de livros: {total}")
    print(f"Disponíveis: {disponiveis}")
    print(f"Emprestados: {emprestados}")
    if emprestados_titulos:
        print("Títulos emprestados:")
        for titulo in emprestados_titulos:
            print(f"- {titulo}")
    else:
        print("Nenhum livro emprestado.")

# ---------------------- Programa Principal ------------------
def main():
    while True:
        print("\n===== MENU =====")
        print("1. Exibir catálogo")
        print("2. Cadastrar novo livro")
        print("3. Buscar por autor")
        print("4. Emprestar/Devolver livro")
        print("5. Contagem de livros")
        print("0. Sair")
        opcao = input("Escolha uma opção: ").strip()
        if opcao == "1":
            exibir_catalogo(catalogo)
        elif opcao == "2":
            cadastrar_livro(catalogo)
            exibir_catalogo(catalogo)
        elif opcao == "3":
            buscar_por_autor(catalogo)
        elif opcao == "4":
            alternar_emprestimo(catalogo)
        elif opcao == "5":
            contagem_livros(catalogo)
        elif opcao == "0":
            relatorio_final(catalogo)
            print("Programa encerrado.")
            break
        else:
            print("Opção inválida. Tente novamente.")

if __name__ == "__main__":
    main()

# ---------------------- Fim do Código ----------------------