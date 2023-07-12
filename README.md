

## Clase WikimediaChangesProducer
Este é um código em Java que define uma classe de serviço chamada WikimediaChangesProducer. Essa classe é responsável por enviar mensagens para um tópico Kafka chamado wikimedia_recenteChange, que contém as mudanças recentes do Wikimedia.

Detalhes do Código:


- Classe de Serviço: A classe é anotada com @Service, indicando que é uma classe de serviço na estrutura Spring.

- Logger: Um Logger é criado para permitir o registro de mensagens.

- Campo kafkaTemplate: A classe possui um campo kafkaTemplate. Esse é uma abstração do Spring para enviar mensagens para tópicos Kafka.

- Construtor: O construtor da classe WikimediaChangesProducer recebe uma instância de KafkaTemplate e a atribui ao campo kafkaTemplate.

- Método sensMessage(): Este método faz o seguinte:

Define o tópico Kafka para onde as mensagens serão enviadas.

Cria um EventHandler chamado eventHandler passando o kafkaTemplate e o tópico.

Define a URL de onde as mudanças recentes do Wikimedia serão obtidas.

Cria um EventSource usando a URL e o eventHandler.

Inicia o EventSource, que então começa a enviar eventos do Wikimedia para o tópico Kafka.

O thread é colocado em sono por 10 minutos usando TimeUnit.MINUTES.sleep(10).

Nota: A classe WikimediaChangeHandler não foi incluída neste código, portanto, a suposição é que ela manipula os eventos recebidos do stream e os envia para o tópico Kafka usando o kafkaTemplate.