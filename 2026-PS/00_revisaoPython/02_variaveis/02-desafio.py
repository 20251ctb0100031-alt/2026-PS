# ============================================
#         SISTEMA DE CONTROLE DE ESTOQUE
# ============================================


# estoque inicial


estoque = {
    "Arroz": 10,
    "Feijão": 5,
    "Macarrão": 8
}


# funao de mostrar o estoque


def mostrar_estoque():
    print("\n📦 Estoque Atual:")
    for produto, quantidade in estoque.items():
        print(f"- {produto}: {quantidade}")
    print("-" * 40)

# validar produto

def obter_quantidade_valida():
    while True:
        entrada = input("Digite a quantidade: ")
        
        try:
            quantidade = int(entrada)
            
            if quantidade < 0:
                print("❌ Quantidade não pode ser negativa.")
            else:
                return quantidade
        
        except ValueError:
            print("❌ Digite apenas números inteiros.")

# lopim inicial

while True:
    
    print("\n=== MENU ===")
    print("1 - Adicionar novo produto")
    print("2 - Mostrar estoque")
    print("3 - Sair")
    
    opcao = input("Escolha uma opção: ")
    
    # adicao de produto

    if opcao == "1":
        nome = input("Digite o nome do produto: ").strip()
        
        if nome == "":
            print("❌ Nome inválido.")
            continue
        
        quantidade = obter_quantidade_valida()
        
        if nome in estoque:
            estoque[nome] += quantidade
        else:
            estoque[nome] = quantidade
        
        print("✅ Produto adicionado!")
    
    # mostra o estoque

    elif opcao == "2":
        mostrar_estoque()
    
    # sair do istema

    elif opcao == "3":
        print("\nEncerrando sistema...")
        
        # Encontrar produto com menor quantidade
        if estoque:
            produto_critico = min(estoque, key=estoque.get)
            print("\n⚠ Produto com menor quantidade em estoque:")
            print(f"{produto_critico} ({estoque[produto_critico]} unidades)")
        
        print("Sistema finalizado.")
        break
    
    # opcao invalida

    else:
        print("❌ Opção inválida. Tente novamente.")