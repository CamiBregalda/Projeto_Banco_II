const formulario = document.querySelector("form");

const nomeCompletoUsuario = document.getElementById('nomeCompleto');
const nomeUsuario = document.getElementById('nomeUsuario');
const cpfUsuario = document.getElementById('cpfUsuario');
const senhaUsuario = document.getElementById('senhaUsuario');
const confirmaSenha = document.getElementById('confirmaSenha');


//contato com o back
/*function cadastrar (){

    fetch("http://localhost:8080/users/cadastrar", {

        headers: {
            'Accept': 'aplication/json',
            'Content-Type': 'application/json'
        },
        method: "POST",

        body: JSON.stringify({
            nomeCompleto: nomeCompletoUsuario.value,
            nome: nomeUsuario.value,
            cpf: cpfUsuario.value,
            senha: senhaUsuario.value
        })
    })

    .then(function(res) { console.log(res) })
    .catch(function(res){ console.log(res) });

};

function limpar() {
    nomeCompletoUsuario.value = "";
    nomeUsuario.value = "";
    cpfUsuario.value = "";
    senhaUsuario.value = "";
}*/


let nomeEValido, senhaEValida = false;

// ver se o nome é válido
//não está rodando - split não funciona (refazer)
function validaNome(nomeCompletoUsuario){
    const nomeComoArray = nomeCompletoUsuario.value.split(' ');
    return nomeComoArray.length >= 2;
}

//ver se as senhas são iguais
function SenhasIguais(senhaUsuario, confirmaSenha){
    console.log("chegou aqui - senhas iguais");
    if (senhaUsuario.value == confirmaSenha){
        return true;
    }
}


formulario.addEventListener('submit', function(evento){
    evento.preventDefault();

    console.log("apertou o botão")

    nomeEValido = validaNome(nomeCompletoUsuario);
    senhaEValida = SenhasIguais(senhaUsuario, confirmaSenha);
    
    
    if(nomeEValido && senhaEValida){
        alert("Ebaaaaaaaaaa")
    } else {
        console.log("Não deu certo")
    }


    //cadastrar();

    /*if ( (nomeEValido == true) && (senhaEValida == true)){
        

    } else if ((nomeEValido == false) && (senhaEValida == true)){
        alert("Nome incompleto")

    } else if ((nomeEValido == true) && (senhaEValida == false)){
        alert("as senhas não são iguais")

    } else{
        alert("Nome incompleto e senhas diferentes")

    }*/

})