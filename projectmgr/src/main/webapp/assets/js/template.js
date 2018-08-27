function templateParserAfter(templateId, data, output){
	var parser = Handlebars.compile( $(templateId).text());
	var resultHTML = parser(data);

	$(output).append(function(){
		return resultHTML; 
	});

}