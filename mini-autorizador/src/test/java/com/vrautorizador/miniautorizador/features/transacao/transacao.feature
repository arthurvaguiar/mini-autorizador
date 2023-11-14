#language: pt


@trasacao
Funcionalidade: Realizar uma transação de cartão.

  Como usuário eu preciso realizar uma transação de cartão.

  Esquema do Cenário:  Transação de Cartão
    Dado que possuo um cartão "<numeroCartao>", "<senha>", <valor>
    Quando eu realizo uma transação no valor de <valor>
    Então a transação é processada

    Exemplos:
    | numeroCartao     | senha | valor |
    | 6549873025634501 | 1234  |  50.0 |
    | 9549873025634501 | 1234  |  50.0 |
    | 6549873025634501 | 0234  |  50.0 |
    | 6549873025634501 | 1234  |  5000.0 |

