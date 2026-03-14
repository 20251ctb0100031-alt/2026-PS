# ==========================================
# SISTEMA DE CÁLCULO DE IMC
# ===========================================  
# Disciplina : Programação de Sistemas (PS)
# Aula       : 06 – Revisão: Funções
# Autor      : Fernando
# Data       : 05/03/2026
# Repositorio:  https://github.com/20251ctb0100031-alt/2026-PS
# ============================================

# DESCRIÇÃO:
# Calcula e classifica o IMC de uma pessoa.
# Demonstra definição de funções, parâmetros,
# retorno, escopo e recursão.
# ================================================

# ---- FUNÇÃO SEM PARÂMETROS E SEM RETORNO ----

def exibir_cabecalho():
    """Exibe o cabeçalho do sistema no terminal."""
    print("=" * 40)
    print(" SISTEMA DE CÁLCULO DE IMC")
    print("=" * 40)

def exibir_rodape():
    """Exibe o rodapé do sistema no terminal."""
    print("=" * 40)
    print(" FIM DO PROGRAMA ".center(40, "="))
    print("=" * 40)

# ---- FUNÇÃO COM PARÂMETROS E RETORNO ----

def calcular_imc(peso, altura):
    """Calcula e retorna o IMC. Fórmula: peso / altura²"""
    imc = peso / (altura ** 2)
    return imc

# ---- ESCOPO LOCAL vs. GLOBAL ----

versao = "1.0"    # variável GLOBAL

def demonstrar_escopo():
    mensagem = "Olá do interior da função"
    print("Dentro da função:")
    print(f"  mensagem = {mensagem}")
    print(f"  versao   = {versao}")

demonstrar_escopo()

print("\nFora da função:")
print(f"  versao = {versao}")
# print(mensagem)  # ERRO: local não existe aqui!

# ---- VALOR PADRÃO E MÚLTIPLOS RETORNOS ----

def classificar_imc(imc, unidade="kg/m²"):
    """Classifica o IMC e retorna classificação e emoji de status."""
    if imc < 18.5:
        classificacao = "Abaixo do peso"
        emoji = "🔵"
    elif imc < 25.0:
        classificacao = "Peso normal"
        emoji = "✅"
    elif imc < 30.0:
        classificacao = "Sobrepeso"
        emoji = "⚠️"
    else:
        classificacao = "Obesidade"
        emoji = "🔴"
    return classificacao, emoji

# Chamada sem o parâmetro opcional (usa o padrão "kg/m²")
imc_teste = 22.5
classificacao, emoji = classificar_imc(imc_teste)
print(f"IMC {imc_teste} ({classificacao}) {emoji}")

# Chamada informando o parâmetro opcional
classificacao, emoji = classificar_imc(imc_teste, unidade="lb/in²")
print(f"Mesma chamada com unidade customizada: {classificacao} {emoji}")

# ---- RECURSÃO BÁSICA ----

def contagem_regressiva(n):
    """Exibe contagem regressiva de n até 0 usando recursão."""
    if n < 0:
        return
    print(n)
    contagem_regressiva(n - 1)

print("\n--- Contagem regressiva ---")
contagem_regressiva(5)

# Fatorial: exemplo clássico de recursão com retorno
def fatorial(n):
    """Calcula n! recursivamente."""
    if n == 0 or n == 1:
        return 1
    return n * fatorial(n - 1)

print("\n--- Fatorial ---")
for i in range(1, 7):
    print(f"{i}! = {fatorial(i)}")

# ---- FUNÇÃO PRINCIPAL ----

def processar_pessoa():
    """Coleta dados, calcula IMC e exibe resultado completo."""
    nome   = input("\nNome: ")
    peso   = float(input("Peso (kg): "))
    altura = float(input("Altura (m): "))

    imc = calcular_imc(peso, altura)
    classificacao, emoji = classificar_imc(imc)

    print("\n--- Resultado ---")
    print(f"Nome           : {nome}")
    print(f"IMC            : {imc:.2f} kg/m²")
    print(f"Classificação  : {classificacao} {emoji}")

# ---- EXECUÇÃO PRINCIPAL ----

exibir_cabecalho()

continuar = "s"
while continuar == "s":
    processar_pessoa()
    continuar = input("\nProcessar outra pessoa? (s/n): ").lower()

exibir_rodape()
