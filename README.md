# Problema 1: Cálculo de Entrega para Livraria

## Contexto
Uma livraria iniciará vendas pela internet e precisa de um sistema para calcular o valor da entrega dos pedidos.

Um pedido pode conter diversos produtos, e cada produto possui:
- nome;
- valor;
- peso.

## Modalidades de entrega
A livraria trabalhará com três modalidades:
- Encomenda PAC;
- Encomenda Sedex;
- Retirada na loja.

## Regras de cálculo do frete
O custo depende do peso total do pedido.

| Tipo de entrega | Faixa de peso | Forma de cálculo |
|---|---|---|
| Encomenda PAC | Até 1 kg | R$ 10,00 |
| Encomenda PAC | De 1 a 2 kg | R$ 15,00 |
| Encomenda PAC | Acima de 2 kg | Não aceita este tipo de entrega |
| Sedex | Até 500 g | R$ 12,50 |
| Sedex | De 500 g até 1.000 g | R$ 20,00 |
| Sedex | Acima de 1 kg | R$ 46,50 + R$ 1,50 para cada 100 g adicional |
| Retirada na loja | Qualquer peso | Não há custo |

## Objetivo
Implementar um sistema que permita ao dono da livraria calcular o valor da entrega conforme as regras acima.

## Requisitos da solução
1. A solução pode ser implementada em qualquer linguagem de programação.
2. Adicionar testes unitários com JUnit para comprovar o funcionamento da aplicação.
3. Criar um diagrama de classes que represente a implementação da solução.

## Critérios de avaliação
- Qualidade da solução;
- Fidelidade do diagrama de classes em relação à implementação;
- Correta diagramação do diagrama de classes seguindo UML 2.

## Entrega
Criar um pull request no repositório da equipe e colocar o link do pull request na tarefa do AVA.
