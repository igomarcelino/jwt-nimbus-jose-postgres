🛡️ Como funciona a segurança deste projeto

Para garantir que nossa API seja segura e eficiente, utilizamos o Spring Security com a estratégia de autenticação via JWT (JSON Web Token). O fluxo é totalmente stateless (sem estado), o que significa que o servidor não precisa salvar sessões na memória, tornando a aplicação mais leve e fácil de escalar.
O fluxo de autenticação, passo a passo:

    A Porta de Entrada: Toda requisição HTTP passa primeiro pelo SecurityFilterChain. Ele funciona como um filtro de segurança: se a requisição não tiver um token válido, ela é barrada ali mesmo, protegendo sua lógica de negócio.

    Validação do Token: Quando uma requisição traz um token, o sistema utiliza o JwtDecoder (configurado com chaves de criptografia) para confirmar se aquele token realmente foi emitido pela nossa aplicação e se ele não está expirado ou alterado.

    Contexto da Requisição: Se o token for validado, o sistema cria um objeto de Authentication que representa o usuário e o armazena no SecurityContextHolder. Isso é como um "crachá" temporário que vale apenas para aquela requisição específica.

    Autorização: Com o "crachá" em mãos, o Spring verifica se o usuário tem permissão para acessar aquele recurso específico (por exemplo, se um usuário comum está tentando acessar uma rota de administrador). Se ele não tiver permissão, o acesso é negado.

    Limpeza: Assim que a requisição termina de ser processada e a resposta é enviada, esse "crachá" é removido da memória automaticamente, garantindo que os dados de um usuário nunca se misturem com os de outro.

Por que esta abordagem?

    Segurança Fail-Fast: A segurança roda antes de qualquer lógica de negócio, economizando processamento.

    Independência: O servidor não precisa "lembrar" quem é você em cada requisição; o próprio token carrega todas as informações necessárias.

    Padrão de Mercado: É a arquitetura recomendada para aplicações modernas e APIs que precisam ser seguras e rápidas.