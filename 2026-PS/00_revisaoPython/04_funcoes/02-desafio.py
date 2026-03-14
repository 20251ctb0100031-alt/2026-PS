"""
INSTITUTO FEDERAL DO PARANÁ - IFPR
Componente Curricular: Lógica de Programação
Estudante: Fernando
"""

# --- DEFINIÇÃO DAS FUNÇÕES ---

def calcular_media(n1, n2):
    """Retorna a média aritmética simples."""
    return (n1 + n2) / 2

def verificar_situacao(media):
    """Critérios IFPR: Aprovado >= 6, Recuperação 4-5.9, Reprovado < 4."""
    if media >= 6.0:
        return "Aprovado"
    elif 4.0 <= media < 6.0:
        return "Recuperação"
    else:
        return "Reprovado"

def solicitar_notas(nome_aluno):
    """Solicita e valida notas entre 0 e 10."""
    notas = []
    for i in range(1, 3):
        while True:
            try:
                nota = float(input(f"Digite a {i}ª nota de {nome_aluno}: "))
                if 0 <= nota <= 10:
                    notas.append(nota)
                    break
                print("Erro: A nota deve estar entre 0 e 10.")
            except ValueError:
                print("Erro: Digite um valor numérico válido.")
    return notas[0], notas[1]

def calcular_media_turma(lista_medias, index=0):
    """Calcula a média geral da turma usando RECURSÃO (sem sum ou for)."""
    if not lista_medias:
        return 0
    
    def somar_recursivo(lista):
        if not lista:
            return 0
        return lista[0] + somar_recursivo(lista[1:])
    
    soma_total = somar_recursivo(lista_medias)
    return soma_total / len(lista_medias)

def resumo_turma(alunos):
    """Contabiliza situações dos alunos na lista."""
    aprov = rec = repr_ = 0
    for aluno in alunos:
        sit = aluno['situacao']
        if sit == "Aprovado": aprov += 1
        elif sit == "Recuperação": rec += 1
        else: repr_ += 1
    return aprov, rec, repr_

def gerar_relatorio_individual(nome, media, situacao):
    """Exibe os dados formatados do aluno."""
    print(f"Aluno: {nome:<10} | Média: {media:>4.1f} | Situação: {situacao}")

# --- EXECUÇÃO PRINCIPAL ---

def main():
    lista_alunos = []
    total_estudantes = 3 # Definido conforme critério do Nível B
    
    print("--- ENTRADA DE DADOS ---")
    for _ in range(total_estudantes):
        nome = input("\nNome do aluno: ")
        n1, n2 = solicitar_notas(nome)
        media = calcular_media(n1, n2)
        situacao = verificar_situacao(media)
        
        lista_alunos.append({
            "nome": nome,
            "media": media,
            "situacao": situacao
        })

    print("\n" + "="*50)
    print("      RELATÓRIO FINAL DA TURMA")
    print("="*50)
    
    # Processa relatórios individuais
    for aluno in lista_alunos:
        gerar_relatorio_individual(aluno['nome'], aluno['media'], aluno['situacao'])

    # Cálculos avançados
    todas_medias = [a['media'] for a in lista_alunos]
    media_geral = calcular_media_turma(todas_medias)
    ap, rc, rp = resumo_turma(lista_alunos)

    print("-" * 50)
    print(f"Média Geral da Turma: {media_geral:.2f}")
    print(f"Total Aprovados:      {ap}")
    print(f"Total Recuperação:    {rc}")
    print(f"Total Reprovados:     {rp}")
    print("="*50)

if __name__ == "__main__":
    main()
