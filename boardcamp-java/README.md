# Welcome to Boardcamp API
The API for the lovers of board games. Boardcamp API handles REST requests for board games rentals as described below

## Customers

### GET /customers/:customerId
```
{
  id: 1,
  name: 'João Alfredo',
  cpf: '01234567890'
}
```

### POST /customers
```
{
  name: 'João Alfredo',
  cpf: '01234567890'
}
```
#### Response
```
{
	id: 1,
  name: 'João Alfredo',
  cpf: '01234567890'
}
```

## Games

### GET /games
```
[
  {
    id: 1,
    name: 'Banco Imobiliário',
    image: 'http://',
    stockTotal: 3,
    pricePerDay: 1500
  },
  {
    id: 2,
    name: 'Detetive',
    image: 'http://',
    stockTotal: 1,
    pricePerDay: 2500
  },
]
```

### POST /games
```
{
  name: 'Banco Imobiliário',
  image: 'http://www.imagem.com.br/banco_imobiliario.jpg',
  stockTotal: 3,
  pricePerDay: 1500
}
```
#### Response
```
{
	id: 1,
  name: 'Banco Imobiliário',
  image: 'http://www.imagem.com.br/banco_imobiliario.jpg',
  stockTotal: 3,
  pricePerDay: 1500
}
```

## Rentals

### GET /rentals
```
[
  {
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: null, // troca pra uma data quando já devolvido
    originalPrice: 4500,
    delayFee: 0, // troca por outro valor caso tenha devolvido com atraso
    customer: {
      id: 1,
      name: 'João Alfredo',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
		  name: 'Banco Imobiliário',
		  image: 'http://www.imagem.com.br/banco.jpg',
		  stockTotal: 3,
		  pricePerDay: 1500
    }
  }
]
```
### POST /rentals
```
{
  customerId: 1,
  gameId: 1,
  daysRented: 3
}
```
#### Response
```
{
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: null, 
    originalPrice: 4500,
    delayFee: 0, 
    customer: {
      id: 1,
      name: 'João Alfredo',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
		  name: 'Banco Imobiliário',
		  image: 'http://www.imagem.com.br/banco.jpg',
		  stockTotal: 3,
		  pricePerDay: 1500
    }
  }
```
### PUT /rentals/:rentalId/return
```
{
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: '2021-06-25', 
    originalPrice: 4500,
    delayFee: 3000, 
    customer: {
      id: 1,
      name: 'João Alfredo',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
		  name: 'Banco Imobiliário',
		  image: 'http://www.imagem.com.br/banco.jpg',
		  stockTotal: 3,
		  pricePerDay: 1500
    }
  }
```
