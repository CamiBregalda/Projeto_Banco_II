const formulario = document.querySelector("form");

const nomeCompletoUsuario = document.getElementById('nomeCompleto');
const nomeUsuario = document.getElementById('nomeUsuario');
const cpfUsuario = document.getElementById('cpfUsuario');
const senhaUsuario = document.getElementById('senhaUsuario');
const confirmaSenha = document.getElementById('confirmaSenha');


//contato com o back
function cadastrar (){

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

let nomeEValido, senhaEValida = false;

// ver se o nome é válido
function validaNome(nomeCompletoUsuario){
    const nomeComoArray = nomeCompleto.split(' ');
    return nomeComoArray.length >= 2;        
}
//ver se as senhas são iguais
function SenhasIguais(senhaUsuario, confirmaSenha){
    if (senhaUsuario.value == confirmaSenha){
        return true;
    }
}

//tenta validar tudo
function validarTudo (){
    nomeEValido = validaNome(nomeCompletoUsuario);
    senhaEValida = SenhasIguais(senhaUsuario, confirmaSenha);

    if ( (nomeEValido == true) && (senhaEValida == true)){

    }

}

formulario.addEventListener('submit', function(evento){
    evento.preventDefault();

    

    cadastrar();

})