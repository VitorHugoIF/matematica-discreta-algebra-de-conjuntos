<%-- 
    Document   : operation
    Created on : 04/03/2019, 17:48:49
    Author     : 55329
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h3 class="h3Blue">Operações em conjuntos</h3>
    <hr>

    <div id="contentOperation">
        <form id="formOperations">
            <div id="divOperations">
                <label><h4>Esolha a operação:</h4></label>
                <div class="form-row">
                    <div class="form-group col-md-6">

                        <div class="custom-control custom-radio">
                            <input type="radio" id="pertinence" name="radioOperation" value="pertinence" class="custom-control-input">
                            <label class="custom-control-label" for="pertinence">Pertence, não pertence</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="contained" name="radioOperation" value="contained" class="custom-control-input">
                            <label class="custom-control-label" for="contained">Contido, não contido</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="containedProperly" name="radioOperation" value="containedProperly" class="custom-control-input">
                            <label class="custom-control-label" for="containedProperly">Contido, não contido (Propiamente)</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="union" name="radioOperation" value="union" class="custom-control-input">
                            <label class="custom-control-label" for="union">Uniao</label>
                        </div>
                    </div>

                    <div class="form-group col-md-6">

                        <div class="custom-control custom-radio">
                            <input type="radio" id="intersection" name="radioOperation" value="intersection" class="custom-control-input">
                            <label class="custom-control-label" for="intersection">Interseção</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="cartesianProduct" name="radioOperation" value="cartesianProduct" class="custom-control-input">
                            <label class="custom-control-label" for="cartesianProduct">Produto cartesiano</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="setOfParties" name="radioOperation" value="setOfParties" class="custom-control-input">
                            <label class="custom-control-label" for="setOfParties">Conjunto das partes</label>
                        </div>

                    </div>
                </div>
            </div>

            <div id="divSetsElements">
                <label><h4>Esolha o(s) conjunto(s) ou elemento(s):</h4></label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <select name="objects1" id="objects1" class="form-control">

                        </select>
                    </div>

                    <div class="form-group col-md-6">
                        <select name="objects2" id="objects2" class="form-control">

                        </select>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary" value="OK">
            </div>

        </form>
        <div id="result" style="margin-top: 15px;">

        </div>
        <div id="tableOperations" style="margin-top: 15px;">
            <table class="table table-bordered table-bordered table-sm">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Conjunto</th>
                    </tr>
                </thead>
                <tbody id="bodyTableOperations">

                </tbody>
            </table>
        </div>
    </div>

</div>
<script src="js/operation.js"></script>