const formulario = document.querySelector("form");

const nomeCompletoUsuario = document.getElementById('nomeCompleto');
const nomeUsuario = document.getElementById('nomeUsuario');
const cpfUsuario = document.getElementById('cpfUsuario');
const senhaUsuario = document.getElementById('senhaUsuario');
const confirmaSenha = document.getElementById('confirmaSenha');

function cadastrar (){
    
}

formulario.addEventListener('submit', function(evento){
    evento.preventDefault();

   
    const dadosCadastro = {
        nomeCompleto: nomeCompletoUsuario.value,
        nome: nomeUsuario.value,
        cpf: cpfUsuario.value,
        senha: senhaUsuario.value
    }

    console.log(dadosCadastro);
})