JAddressApi
===========

Api(Servlet) para consulta de CEP diretamente da base dos Correios

Exemplos de chamada:
http://localhost:8080/JAddressApi/CorreiosRepository?end=13171480
OU
http://localhost:8080/JAddressApi/CorreiosRepository?end=Emilio%20Leao%20Brambila

RETORNO[JSON]:
{"zipCode":"13171480",
"typeOfStreet":"Rua",
"street":"Emílio Leão Brambila",
"neighborhood":"Vila Menuzzo",
"city":"Sumaré",
"estate":"SP"}

Projeto baseado no AddressApi que pode ser encontrado em:
https://github.com/emersonsoares/AddressAPI

