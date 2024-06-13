const form = document.getElementById("form-cadastro");

const nomeFuncionario = document.getElementById('nomeCompleto');
const cpfFuncionario = document.getElementById('cpf-funcionario');
const senhaFuncionario = document.getElementById('senha');
const cargoFuncionario = document.getElementById('cargo');


form.addEventListener('submit', function(evento){
    evento.preventDefault();


        const dadosFuncionario = {
            nome: nomeFuncionario.value,
            cpf: cpfFuncionario.value,
            senha: senhaFuncionario.value, 
            cargo: cargoFuncionario.value
        }

})


function cadastrar(){

    fetch(JSON)
}
