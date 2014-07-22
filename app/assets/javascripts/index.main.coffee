usuarioSelecionadoSel = '.usuario-selecionado:checked'
@thiz = this

$(document).ready ->
  $(document).on "click", ".editar-usuario", editar

arrayToJson = (array) ->
 obj[name] = value for value, name in array
 obj

editar = (event) ->
 do event.preventDefault
 idSelecionado = do $(usuarioSelecionadoSel).val
 if idSelecionado 
  jsRoutes.controllers.Application.editar(idSelecionado).ajax
   context: this
   success: (data) ->
    exec = (name, value) ->
     comp = $("input[name='#{name}']")
     if comp and comp.length > 0
      comp.val value
     else
      comp = $("select[name='#{name}']")
      $("select[name='#{name}'] option[value='#{value}']").prop "selected", true if comp and comp.length > 0
    exec name, value for name, value of data
   error: (error) ->
    alert "Erro: #{error}"
    
    