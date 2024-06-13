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
function validaNome(nomeCompletoUsuario){
    const nomeComoArray = nomeCompletoUsuario.value.split(' ');
    return nomeComoArray.length >= 2;
}

//ver se as senhas são iguais
function SenhasIguais(senhaUsuario, confirmaSenha){
    if (senhaUsuario.value == confirmaSenha.value){
        return true;
    }
}


formulario.addEventListener('submit', function(evento){
    evento.preventDefault();

    nomeEValido = validaNome(nomeCompletoUsuario);
    senhaEValida = SenhasIguais(senhaUsuario, confirmaSenha);

    if(nomeEValido && senhaEValida){
        //cadastrar();
        //mandar para a página inicial
    
        //limpar();
    } else if (!nomeEValido && senhaEValida){
        //avisar que o nome está incompleto
        nomeCompletoUsuario.style.border
        
    } else if (nomeEValido && !senhaEValida){
        //avisar que as senhas não estão iguais
        
    }else{
        //mandar arrumar o nome e as senhas

    }

//display: none
    

})