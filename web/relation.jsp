<%-- 
    Document   : relation
    Created on : 21/03/2019, 20:38:51
    Author     : vitor
--%>

<div>

    <div style="margin-top: 15px;">
        <form id="formSelectArchiveRelations">
            <div class="form-row">
                <div class="form-group custom-file col-md-5">
                    <input type="file" class="form-control-file" id="selectArchiveRelations">
                </div>
            </div> 
        </form>
    </div>
    <hr>
    <h3 class="h3Blue">Relações entre conjuntos</h3>
    <hr>

    <div id="contentRelations">

        <form id="formRelations">
            <div id="divRelations">
                <div class="col-md-12">
                    <label><h4>Esolha a relação:</h4></label>
                    <div class="form-row">
                        <div class="form-group col-md-6">

                            <div class="custom-control custom-radio">
                                <input type="radio" id="bigger" name="radioRelations" value="bigger" class="custom-control-input">
                                <label class="custom-control-label" for="bigger">Maior</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="smaller" name="radioRelations" value="smaller" class="custom-control-input">
                                <label class="custom-control-label" for="smaller">Menor</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="equal" name="radioRelations" value="equal" class="custom-control-input">
                                <label class="custom-control-label" for="equal">Igual</label>
                            </div>

                        </div>
                        <div class="form-group col-md-6">

                            <div class="custom-control custom-radio">
                                <input type="radio" id="raisedTo2" name="radioRelations" value="raisedTo2" class="custom-control-input">
                                <label class="custom-control-label" for="raisedTo2">Quadrado</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="squareRoot" name="radioRelations" value="squareRoot" class="custom-control-input">
                                <label class="custom-control-label" for="squareRoot">Raiz quadrada</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="arbitraryRelationship" name="radioRelations" value="arbitraryRelationship" class="custom-control-input">
                                <label class="custom-control-label" for="arbitraryRelationship">Relação Arbitrária</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="divSets">
                <div class="col-md-12">
                    <label><h4>Conjuntos</h4></label>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <select name="set1" id="set1" class="form-control" readonly>

                            </select>
                        </div>

                        <div class="form-group col-md-6">
                            <select name="set2" id="set2" class="form-control" readonly>

                            </select>
                        </div>
                    </div>
                </div>                        
            </div>
            <div id="divPairs">
                <div class="col-md-12">
                    <label><h4>Pares</h4></label>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <input type="text" name="pairs" id="pairs" class="form-control" aria-describedby="pairsHelpBlock" placeholder="(1,2);(3,4)">
                            <small id="pairsHelpBlock" class="form-text text-muted">
                                Represente os pares entre parenteses, separando os elementos por virgula(,) e cada par por ponto e virgula(;)
                            </small>
                        </div>
                    </div>
                </div>
            </div>
            <div id="divSubmit">
                <div class="col-md-12">
                    <div class="form-row">
                        <input type="submit" class="btn btn-primary form-group" value="OK" id="btnSubmitRelations">
                    </div>
                </div>
            </div>
            
        </form>

        <div id="tableRelations" style="margin-top: 15px;">
            <table class="table table-bordered table-bordered table-sm">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Conjunto</th>
                    </tr>
                </thead>
                <tbody id="bodyTableRelations">

                </tbody>
            </table>
        </div>

    </div>

</div>
<script src="js/relation.js"></script>