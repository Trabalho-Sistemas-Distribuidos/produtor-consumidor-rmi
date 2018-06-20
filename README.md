#Produtor/Consumidor RMI

## Compilar
`javac -d bin -sourcepath src -cp "./*" src/server/ServerMain.java src/client/ClientMain.java`

## Rodar servidor
`java -cp bin server.ServerMain`

## Rodar cliente
* `java -cp bin client.ClientMain <string>`
<string> = string a ser impressa
* `java -cp bin client.ClientMain`
irá imprimir teste