class @Utils

 @updateForm = (data) ->
  exec = (name, value) ->
   comp = $("input[name='#{name}']")
   if comp and comp.length > 0
    comp.val value
   else
    comp = $("select[name='#{name}']")
    $("select[name='#{name}'] option[value='#{value}']").prop "selected", true if comp and comp.length > 0
  exec name, value for name, value of data 
 
 @arrayToJson = (array) ->
  obj[name] = value for value, name in array
  obj   
    