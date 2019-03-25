package app.model;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SparqlQuery {
    private OntModel model;

    public SparqlQuery(String owl){
        this.model = this.readOntology(owl);

    }


    public OntModel readOntology(String file) {
        InputStream in;
        try {
            in = new FileInputStream(file);
             OntModel model =  ModelFactory.createOntologyModel();
             model.read(in, "RDF/XML");
            in.close();
            return model;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Game> makeQuery(String queryString) {
        ArrayList<Game> resultsLines = new ArrayList<>();
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, this.model);
        ResultSet results = qexec.execSelect();
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            String titreJeu = soln.getResource("titre").toString();
            titreJeu = titreJeu.split("#")[1];

            String nbVente = soln.getLiteral("nbVente").toString();
            nbVente = nbVente.split("\\^")[0];
            String nbJoueurs = soln.getLiteral("nbJoueurs").toString();
            nbJoueurs = nbJoueurs.split("\\^")[0];

            resultsLines.add(new Game(titreJeu ,Integer.parseInt(nbVente) ,Integer.parseInt(nbJoueurs) ));
        }
        return resultsLines;

    }
}
