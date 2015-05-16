$("#userSelectTable tr").click(function(){
   $(this).toggleClass('selected');    
   var value=$(this).find('td:first').html();    
});

$('#submit').on('click', function(e){
    var selected = [];
    $("#userSelectTable tr.selected").each(function(){
        selected.push($('td:first', this).html());
    });
    
    
    // If no rows are selected
    
    if(selected.length)  {
    
        // If name and phone are non empty
    
        if(!$("#InputName").val().length && !$("#InputNomineePhone").val().length)  {
        
            // Change the table value to the selected items/ list
            $("#table").attr("value", selected);
        
        }
        
        else  {
        
            e.preventDefault();
          
        }
    
    } 
    
    else  {
    
        // If both the fields are empty and if no rows are selected prevent default action.
        if(!$("#InputName").val() || !$("#InputNomineePhone").val())  {
        
            e.preventDefault();
        
        }
    
    }
    
    
});