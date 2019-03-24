<%-- 
    Document   : composition
    Created on : 23/03/2019, 20:26:34
    Author     : 55329
--%>

<div>

    <div style="margin-top: 15px;">
        <form id="formSelectArchiveComposition">
            <div class="form-row">
                <div class="form-group custom-file col-md-5">
                    <input type="file" class="form-control-file" id="selectArchiveComposition">
                </div>
            </div> 
        </form>
    </div>
    <hr>
    <h3 class="h3Blue">Relações entre conjuntos(Composta)</h3>
    <hr>

    <div id="contentComposition">

        <form id="formComposition">
            <div id="divComposition">
                <div class="col-md-12">
                    <label><h4>Esolha a relação:</h4></label>
                    <div class="form-row">
                        <div class="form-group col-md-6">

                            <div class="custom-control custom-radio">
                                <input type="radio" id="bigger" name="radioCompositionRelations" value="bigger" class="custom-control-input">
                                <label class="custom-control-label" for="bigger">Maior</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="smaller" name="radioCompositionRelations" value="smaller" class="custom-control-input">
                                <label class="custom-control-label" for="smaller">Menor</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="equal" name="radioCompositionRelations" value="equal" class="custom-control-input">
                                <label class="custom-control-label" for="equal">Igual</label>
                            </div>

                        </div>
                        <div class="form-group col-md-6">

                            <div class="custom-control custom-radio">
                                <input type="radio" id="raisedTo2" name="radioCompositionRelations" value="raisedTo2" class="custom-control-input">
                                <label class="custom-control-label" for="raisedTo2">Quadrado</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="squareRoot" name="radioCompositionRelations" value="squareRoot" class="custom-control-input">
                                <label class="custom-control-label" for="squareRoot">Raiz quadrada</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="arbitraryCompositionRelationship" name="radioCompositionRelations" value="arbitraryCompositionRelationship" class="custom-control-input">
                                <label class="custom-control-label" for="arbitraryCompositionRelationship">Relação Arbitrária</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="divSets">
                <div class="col-md-12">
                    <label><h4>Conjuntos</h4></label>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <select name="set1" id="set1" class="form-control" readonly>

                            </select>
                        </div>

                        <div class="form-group col-md-4">
                            <select name="set2" id="set2" class="form-control" readonly>

                            </select>
                        </div>
                        <div class="form-group col-md-4">
                            <select name="set3" id="set3" class="form-control" readonly>

                            </select>
                        </div>
                    </div>
                </div>                        
            </div>
            <div id="divPairs">
                <div class="col-md-12">
                    <label><h4>Pares</h4></label>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label><h5>R:  A -> B</h5></label>
                            <input type="text" name="pairsR" id="pairsR" class="form-control" aria-describedby="pairsHelpBlock" placeholder="1,2;3,4">
                            <small id="pairsHelpBlock" class="form-text text-muted">
                                Represente os pares separando os elementos por virgula(,) e cada par por ponto e virgula(;)
                            </small>
                        </div>
                        <div class="form-group col-md-6">
                            <label><h5>S:  B -> C</h5></label>
                            <input type="text" name="pairsS" id="pairsS" class="form-control" aria-describedby="pairsHelpBlock" placeholder="1,2;3,4">
                            <small id="pairsHelpBlock" class="form-text text-muted">
                                Represente os pares separando os elementos por virgula(,) e cada par por ponto e virgula(;)
                            </small>
                        </div>
                    </div>
                </div>
            </div>
            <div id="divSubmit">
                <div class="col-md-12">
                    <div class="form-row">
                        <input type="submit" class="btn btn-primary form-group" value="OK" id="btnSubmitComposition">
                    </div>
                </div>
            </div>
            
        </form>

        <div id="tableComposition" style="margin-top: 15px;">
            <table class="table table-bordered table-bordered table-sm">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Pares</th>
                        <th scope="col">Dominio</th>
                        <th scope="col">Imagem</th>
                        <th scope="col">Classificacao</th>
                    </tr>
                </thead>
                <tbody id="bodyTableComposition">

                </tbody>
            </table>
        </div>

    </div>

</div>
<script src="js/composition.js"></script>
