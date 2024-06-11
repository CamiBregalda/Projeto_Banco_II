const form = document.getElementById("form-cadastro");

const nomeFuncionario = document.getElementById('nomeCompleto');
const cpfFuncionario = document.getElementById('cpf-funcionario');

let nomeEValido, cpfEValido = false;


function validaNome(nomeCompleto){
    const nomeComoArray = nomeCompleto.split(' ');
    return nomeComoArray.length >= 2;
}

function validaCPF(cpfValido){
    return cpfValido.length == 11;
}


form.addEventListener('submit', function(evento){
    evento.preventDefault();

    nomeEValido = validaNome(nomeFuncionario.value);
    if (! nomeEValido){
        nomeFuncionario.style.border = '1px solid red'
        const MensagemErro = document.querySelector('.erro');
        MensagemErro.style.display = 'block';
    } else {
        nomeFuncionario.style = '';
        document.querySelector('.erro').style.display = 'none';
    }

    cpfEValido = validaCPF(cpfFuncionario.value);
    if(! cpfEValido){
        cpfFuncionario.style.border= '1px solid red'
        const MensagemErro = document.querySelector('.erro-cpf');
        MensagemErro.style.display = 'block';
    }else {
        nomeFuncionario.style = '';
        document.querySelector('.erro-cpf').style.display = 'none';
    }


})



