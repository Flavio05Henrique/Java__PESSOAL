const clientInformationForm = document.querySelector('[data-registra-cliente-form]')
const listItensBnt = document.querySelector('[data-listar-itens]')
const listItensContainer = document.querySelector('[data-itens-container]')
const url = "http://localhost:8080/cadastraCliente"
let clienteList 

clientInformationForm.addEventListener('submit', event => {
    event.preventDefault()
    const name = event.target.elements['registra_cliente-nome'].value
    const email = event.target.elements['registra_cliente-email'].value
    const phone = event.target.elements['registra_cliente-telefone'].value
    const newCLient = {
        "nome": name,
        "email": email,
        "telefone": phone
    }
    saveClient(newCLient)
})

const saveClient = async (newCLient) => {
    const headers = {
        "Content-Type": "application/json"
    }
    const init = {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(newCLient),
        mode: 'cors'
        }; 
    try {
        const response = await fetch(url, init)
        console.log(response)
    } catch (e) {
        console.log(e)
    }
}

listItensBnt.addEventListener('click', event => {
    clearContainer(listItensContainer)
    listItens()
})

const listItens = async () => {
    const init = {
        method: 'GET'
    }
    try{
        const response = await fetch(url, init)
        const final = await response.json()
        clienteList = final.content
        createItensInContainer(clienteList)
    } catch (e) {
        console.log(e)
    }
}

const clearContainer = (container) => {
    container.innerHTML = ''
}

const createItensInContainer = (itens) => {
    for(let i = 0; i < itens.length; i++){
        listItensContainer.innerHTML += `
            <div class="itens" id="${itens[i].id}">
                <div class="intens_cliente_info">
                    <div class="itens_nome itens_cliente" title="${itens[i].nome}">${itens[i].nome}</div>
                    <div class="itens_email itens_cliente" title="${itens[i].email}">${itens[i].email}</div>
                    <div class="itens_telefone itens_cliente">${itens[i].telefone}</div>
                </div>
                <div class="itens_bnts">
                    <button class="bnt_atualiza_item bnt_item" value="atualiza"></button>
                    <button class="bnt_deleta_item bnt_item" value="deleta"></button>
                </div>
            </div>
        `
    }
}

listItensContainer.addEventListener('click', event => {
    const elementClicked = event.target
    const elementClickedContainer = elementClicked.parentNode.parentNode
    const id = elementClickedContainer.id
    if(elementClicked.tagName == 'BUTTON'){
        switch (elementClicked.value) {
            case 'atualiza': 
                changeClienteInfo(elementClickedContainer);
                break;
            case 'deleta':
                deleteCliente(id);
                break;
            default:
                console.log('valor invalido',)
        }
    }
})

const deleteCliente = async (id) => {
    const init = {
        method: 'DELETE'
    }
    const urlWithId = `http://localhost:8080/cadastraCliente/${id}`
    const response = await fetch(urlWithId, init)
    clearContainer(listItensContainer)
    listItens()
}

const changeClienteInfo = (elementClickedContainer) => {
    const index = clienteList.findIndex(element => element.id == elementClickedContainer.id)
    const cliente = clienteList[index]

    elementClickedContainer.innerHTML = `
        <form class="form_muda_cliente_info" data-form-muda-cliente-info> 
            <input type="text" id="muda_nome" class="muda_nome muda_cliente_info" required maxlength="50" value="${cliente.nome}">
            <input type="text" id="muda_email" class="muda_email muda_cliente_info" required maxlength="50" value="${cliente.email}">
            <input type="tel" id="muda_telefone" class="muda_telefone muda_cliente_info" required maxlength="50" value="${cliente.telefone}">
            <button type="submit" class="bnt_muda_cliente_info"></button>
        </form>
    `

    const form = elementClickedContainer.querySelector('[data-form-muda-cliente-info]')
    form.addEventListener('submit', event => {
        event.preventDefault()
        const name = event.target.elements['muda_nome'].value
        const email = event.target.elements['muda_email'].value
        const phone = event.target.elements['muda_telefone'].value
        const cliente = {
            "id":elementClickedContainer.id,
            "nome": name,
            "email": email,
            "telefone": phone
        }
        changeClienteInfoRequeste(cliente)
        clearContainer(listItensContainer)
        listItens()
    })
}

const changeClienteInfoRequeste = async (cliente) => {
    const headers = {
        "Content-Type": "application/json"
    }
    const init = {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(cliente),
        mode: 'cors'
        }; 
    try {
        const response = await fetch(url, init)
        console.log(response)
    } catch (e) {
        console.log(e)
    }
}