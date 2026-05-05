'''
=======================
# ARQUIVO: pet.py
# Diciplina : Progamação de sistemas (2026-2)
# Aula : aula 20 - por que POO?
# Autor : Fernando
# Conceitos : Classes, objetos, atributos, métodos, encapsulamento
# Atividade : classe Pet
# =======================
'''

class Pet:
    def __init__(self, nome, especie, idade, raca, peso, nome_dono=None, vacinado=False):
        self.nome = nome
        self.especie = especie
        self.idade = idade
        self.peso = peso
        self.raca = raca
        self.nome_dono = nome_dono
        self.vacinado = vacinado
        self.hospedado = False

    def exibir_dados(self):
        print("\n---Dados do Pet---")   
        print(f"Nome: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade} anos")
        print(f"Peso: {self.peso} kg")
        print(f"Raça: {self.raca}")
        print(f"Dono: {self.nome_dono}")
        print(f"Vacinado: {'Sim' if self.vacinado else 'Não'}")
        print(f"Hospedado: {'Sim' if self.hospedado else 'Não'}")


    def registrar_entrada(self):
        if self.hospedado:
            print(f"{self.nome} já está hospedado no hotel.")
        else:
            self.hospedado = True
            print(f"{self.nome} entrou no hotel.")

    def registrar_saida(self):
        self.hospedado = False
        print(f"{self.nome} saiu do hotel.")

    def calcular_diaria(self):
        if self.hospedado:
            if self.idade <= 3:
                return 30
            if self.idade <= 10:
                return 60
            else:
                return 75
    
    def verificar_vacinacao(self):
        if self.hospedado:
            
            if self.vacinado:
                print(f"{self.nome} está vacinado.")
            else:
                print(f"{self.nome} não está vacinado.")
        
    def atualizar_peso(self, novo_peso):
        self.peso = novo_peso
        print(f"O peso de {self.nome} foi atualizado para {self.peso} kg.")


    def emitir_resumo(self):
        print("\n---Resumo do Pet---")
        print(f"Nome: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade} anos")
        print(f"Peso: {self.peso} kg")
        print(f"Raça: {self.raca}")
        print(f"Vacinado: {'Sim' if self.vacinado else 'Não'}")
        print(f"Hospedado: {'Sim' if self.hospedado else 'Não'}")


pet1 = Pet("abobora", "macaco🐒", 5, "orangotango", 22.0, "luiz carlos", True)
pet2 = Pet("joaoV", "jacaré🐊", 2, "lacaoste", 4.2, "joaoP", True)
pet3 = Pet("yuri", "calopsita🕊️", 11, "albina", 18.0, "antony", False)

pet1.exibir_dados()
pet1.registrar_entrada()
pet1.verificar_vacinacao()
print("Diária:", pet1.calcular_diaria())
pet1.atualizar_peso(35.0)
pet1.emitir_resumo()


pet2.exibir_dados()
pet2.registrar_entrada()
pet2.verificar_vacinacao()
print("Diária:", pet2.calcular_diaria())
pet2.atualizar_peso(4.5)
pet2.emitir_resumo()

pet3.exibir_dados()
pet3.registrar_entrada()
pet3.verificar_vacinacao()
print("Diária:", pet3.calcular_diaria())
pet3.atualizar_peso(23.0)
pet3.emitir_resumo()
