JAddressApi
===========

Api(Servlet) para consulta de CEP diretamente da base dos Correios

### Exemplos de Chamada:
```sh
CEP:
  http://localhost:8080/JAddressApi/CorreiosRepository?end=13171480

Logradouro:
  http://localhost:8080/JAddressApi/CorreiosRepository?end=Emilio%20Leao%20Brambila
```
### RETORNO [JSON]:
```sh
{
  "zipCode":"13171480",
  "typeOfStreet":"Rua",
  "street":"Emílio Leão Brambila",
  "neighborhood":"Vila Menuzzo",
  "city":"Sumaré",
  "estate":"SP"
}
```
### Novidades 
```sh
- Usando nova url para coletar os dados (Mais Estável)
- Implementação do JUnit 
```

Projeto baseado no AddressApi que pode ser encontrado em:
https://github.com/emersonsoares/AddressAPI
