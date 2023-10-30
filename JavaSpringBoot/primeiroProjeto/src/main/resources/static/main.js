const listar = async () => {
    const url = "http://localhost:8080/listarEventos"
    const resposta = await fetch(url)
//    const jsonRes = awiat resposta.json()
    console.log(resposta)


}
listar()