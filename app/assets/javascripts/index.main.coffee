usuarioSelecionadoSel = '.usuario-selecionado:checked'

componentes =
 tabelaUsuarios: $("#tabela-usuarios")
 linhaTemplate: $("#tabela-usuarios tr.template")
 linhaTabelaVazia: $("#tabela-usuarios #tabela-vazia")
 paginador: $("#paginador-usuario")

UsuarioRouter = Backbone.Router.extend
 routes:
  "usuarios/editar/:id":           "editar"
  "usuarios/:resultados/:pagina":  "listar"
  "usuarios/:pagina":              "listarPagina"
   
router = new UsuarioRouter

router.on "route:editar", (id) -> editar id

router.on "route:listarPagina", (pagina) -> 
 itens = do $("#usuario-registros-por-pagina").val
 router.navigate "usuarios/#{itens}/#{pagina}", trigger: true

router.on "route:listar", (resultados, pagina) -> 
 $("#usuario-registros-por-pagina").val resultados
 listarPagina pagina, resultados
 

$(document).ready ->
  do listar


  $(document).on "click", "button.editar-usuario", (event) ->
   do event.preventDefault if event
   id = do $(usuarioSelecionadoSel).val
   router.navigate "usuarios/editar/#{id}", trigger: true
   
  $(document).on "click", "button.remover-usuario", (event) ->
   do event.preventDefault if event
   id = do $(usuarioSelecionadoSel).val
   remover id
  $(document).on "change", "#usuario-registros-por-pagina", (e) ->
   itens = do $(this).val
   router.navigate "usuarios/#{itens}/1", trigger: true
 
  do Backbone.history.start
  

remover = (id) ->
 if id
  jsRoutes.controllers.Application.remover(id).ajax
   context: this
   success: (data) ->
    do listar
   error: (error) ->
    $(document).html error.responseText

editar = (id) ->
 if id 
  jsRoutes.controllers.Application.editar(id).ajax
   context: this
   success: (data) ->
    Utils.updateForm data
   error: (error) ->
    $(document).html error.responseText

listar = (event) ->
 do event.preventDefault if event
 listarPagina 1, 3
 
listarPagina = (pag, resultados) ->
 jsRoutes.controllers.Application.listar(pag, resultados).ajax
  context: this
  success: (data) ->
   tbody = componentes.tabelaUsuarios.find "tbody"
   tbody.html componentes.linhaTemplate
   for model in data.itens
    linha = do componentes.linhaTemplate.clone
    linha.removeClass "template"
    for name, value of model
     span = linha.find "span.usuario-#{name}"
     if span and span.length > 0
      span.html value
     else
      span = linha.find "input.usuario-#{name}"
      span.val value if span and span.length > 0
    tbody.append linha
   componentes.paginador.html data.paginador
  error: (error) ->
   $(document).html error.responseText
    
    