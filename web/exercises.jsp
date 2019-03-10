<div>
   
    <h3 class="h3Blue">Exercicios</h3>
    <hr>

    <div id="contentOperation">
        <div class="row">
            <h4>Exercicio 1:</h4>
            <p class="text-justify">
                Os times 4, 8, 12 e 16 do estado de Pernambuco devem jogar com os times 3, 7, 11 e 15 do estado do Para. Cada
                time de Pernambuco deve jogar uma vez com cada time do Para. Exiba os jogos que devem ser realizados.
            </p>
            <div class="form-group col-md-6">
                <input type="text" name="objects1Exercise1" id="object1Exercise1" class="form-control" readonly></input>
            </div>
            <div class="form-group col-md-6">
                <input type="text" name="objects2Exercise1" id="object2Exercise1" class="form-control" readonly></input>
            </div>
        </div>
        <div class="row">
            <h4>Exercicio 2:</h4>
            <p class="text-justify">
                Suponha que voce tem o codigo (numerico) dos alunos que obtiveram media na disciplina A, o codigo dos alunos
                com media na disciplina B e o codigo dos alunos com media na disciplina C. O criterio de aprovacao na escola exige
                media em todas as disciplinas para aprovcao. Exiba a lista dos alunos aprovados. Exiba a lista dos alunos com
                media em pelo menos uma disciplina.
            </p>
            <div class="form-group col-md-4">
                <input type="text" name="objects1Exercise2" id="object1Exercise2" class="form-control" readonly></input>
            </div>
            <div class="form-group col-md-4">
                <input type="text" name="objects2Exercise2" id="object2Exercise2" class="form-control" readonly></input>
            </div>
            <div class="form-group col-md-4">
                <input type="text" name="objects3Exercise2" id="object3Exercise2" class="form-control" readonly></input>
            </div>
        </div>
        <div class="row">
            <h4>Exercicio 3:</h4>
            <p class="text-justify">
                Uma empresa tem a seguinte poltica de premiacao de fim de ano: ganha premio o funcionario que tiver batido sua
                meta no primeiro ou no segundo semestre ou em ambos e que tenha sido o melhor vendedor em pelo menos um mes.
                De posse do codigo do funcionario, da relacao de funcionarios cumpridores da meta em cada semestre e da relacao
                de melhores vendedores do mes, informe se este funcionario recebera ou nao premio de fim de ano.
            </p>
            <div class="form-group col-md-2">
                <input type="text" name="objects1Exercise3" id="object1Exercise3" class="form-control" readonly></input>
            </div>
            <div class="form-group col-md-3">
                <input type="text" name="objects2Exercise3" id="object2Exercise3" class="form-control" readonly></input>
            </div>
            <div class="form-group col-md-3">
                <input type="text" name="objects3Exercise3" id="object3Exercise3" class="form-control" readonly></input>
            </div>
            <div class="form-group col-md-4">
                <input type="text" name="objects4Exercise3" id="object4Exercise3" class="form-control" readonly></input>
            </div>
        </div>
        <div class="row">
            <select name="Selectexercises" id="Selectexercises" class="form-control">
                <option value="1">Exercicio 1</option>
                <option value="2">Exercicio 2</option>
                <option value="3">Exercicio 3</option>
            </select>
        </div>
        <div class="row" style="margin-top: 10px;">
            <button type="button" class="btn btn-primary" id="btnSolveExercises">Resolver</button>
        </div>
        
        <div id="tableExercises" style="margin-top: 15px;">
            <table class="table table-bordered table-bordered table-sm">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Exercicio</th>
                        <th scope="col">Solucao</th>
                    </tr>
                </thead>
                <tbody id="bodyTableExercises">

                </tbody>
            </table>
        </div>
        
    </div>

</div>
<script src="js/exercises.js"></script>