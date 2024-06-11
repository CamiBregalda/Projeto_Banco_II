const form = document.getElementById("form-cadastro");

function validaNome(nomeCompleto){
    const nomeComoArray = nomeCompleto.split(' ');
    return nomeComoArray.length >= 2;
}

form.addEventListener('submit', function(e){
    e.preventDefault;

    const nomeFuncionario = document.getElementsByName('firstname');
    const cpfFuncionario = document.getElementsByName("cpf");
    const senhaFuncionario = document.getElementsByName("password");
    const cargoFuncionario = document.getElementsByName("hole");
})

console.log(form);
