
import pickle

class Contato:
    """Representa um contato da agenda."""

    def __init__(self, animal, raça, nome, nomeDono, peso, idade):
        self.animal = animal
        self.raça = raça
        self.nome = nome
        self.nome_do_dono = nomeDono
        self.peso = peso
        self.idade = idade

    def exibir(self):
        print(f" Animal : {self.animal}")
        print(f" Raça: {self.raça}")
        print(f" Nome : {self.nome}")
        print(f" Nome do Dono : {self.nome_do_dono}")
        print(f" Peso : {self.peso}")
        print(f" Idade : {self.idade}")

    def para_linha_txt(self):

        return f"{self.animal};{self.raça};{self.nome};{self.nome_do_dono};{self.peso};{self.idade}"

    #-------------------------------
    # Funções de persistência em texto(txt)
    #-------------------------------
    
def salvar_em_txt(contatos, caminho):
    with open(caminho, "w", encoding="utf-8") as arquivo:
        for c in contatos:
            arquivo.write(c.para_linha_txt() + "\n")
            print(f"✅ {len(contatos)} contatos(s) salvo(s) em {caminho}")


def carregar_de_txt(caminho):
    contatos = []
    try:
        with open(caminho, "r", encoding="utf-8") as arquivo:
            for linha in arquivo:
                linha = linha.strip()
                if not linha:
                    continue
                partes = linha.split(";")

                animal, raça, nome, nome_do_dono, peso, idade = partes[0], partes[1], partes[2], partes[3], float(partes[4]), int(partes[5])

                contatos.append(Contato(animal, raça, nome, nome_do_dono, peso, idade))
    except FileNotFoundError:
            print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
    return contatos

# ---------------------------------------------------------
# Funções de persistência binária (pickle)
# ---------------------------------------------------------
''
def salvar_em_binario(contatos, caminho):
    with open(caminho, "wb") as arquivo:
        pickle.dump(contatos, arquivo)
        print(f"✅ {len(contatos)} contatos(s) salvo(s) em {caminho}")

def carregar_de_binario(caminho):
    try:
        with open(caminho, "rb") as arquivo:
            return  pickle.load(arquivo)
    except FileNotFoundError:
        print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
        return []

#===================================
#CRUD EM MEMÓRIA
#===================================
def cadastrar(contatos):
    print("\n---Criar Contato---")
    animal = input("animal      : ")
    raça = input("raça   : ")
    nome = input("nome      : ")
    nome_dono = input("nome do dono : ")
    peso = float(input("peso : "))
    idade = int(input("idade : "))
    contatos.append(Contato(animal, raça, nome, nome_dono, peso, idade))
    print(f"✅ pet Cadastrado")

def listar (contatos):
    if not contatos:
        print( "\n hotel vazio)")
        return
    print(f"\n--- Agenda ({len(contatos)} contato(s)) ---")
    for i, c in enumerate(contatos, start=1):
        print(f"\n[{i}]")
        c.exibir()

def remover(contatos):
    listar(contatos)
    if not contatos:
        return
    indice = int(input("\nNº do contato a remover: ")) - 1
    if 0 <= indice < len(contatos):
            removido = contatos.pop(indice)
            print(f"✅ Contato '{removido.nome}' Removido")
    else:
            print("Índice inválido.")



#===================================
#MENU PRINCIPAL
#===================================

def menu():

    contatos = carregar_de_binario("pets.bin")

    while True: 
        print("\n========== PETS ==========")
        print("1 - Cadastrar pet")
        print("2 - Listar pets")
        print("3 - Remover pet")
        print("4 - Salvar em .txt")
        print("5 - Salvar em binário")
        print("0 - Sair")
        opcao = input("Opção: ")

        if opcao == "1":
            cadastrar(contatos)
        elif opcao == "2":
            listar(contatos)
        elif opcao == "3":
            remover(contatos)
        elif opcao == "4":
            salvar_em_txt(contatos, "pets.txt")
        elif opcao == "5":
            salvar_em_binario(contatos, "pets.bin")
        elif opcao == "0":

            salvar_em_binario(contatos, "pets.bin")
            print("Até logo!")
            break
        else:
            print("Opção inválida.")

# ================================================================
# PONTO DE ENTRADA
# ================================================================

if __name__ == "__main__":
    menu()

