package com.triangle.test.algobatch;

import com.triangle.test.domain.enums.TriangleState;
import com.triangle.test.model.Triangle;
import com.triangle.test.utils.Utils;
import org.springframework.batch.item.ItemProcessor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Processor implements ItemProcessor<List<Triangle>,List<Triangle>> {

    @Override
    public List<Triangle> process(List<Triangle> trianglesToProcess) throws Exception{
        List<Triangle> processedTriangles = null;
        if(trianglesToProcess!=null) {processedTriangles = new ArrayList<Triangle>();}

        for (Triangle triangle:trianglesToProcess){
             System.out.println("\nPROCESSING TRIANGLE :"  + triangle.toString());
             double hipotenuse = Math.sqrt(Math.pow(triangle.getEdgeOne(),2) + Math.pow(triangle.getEdgeTwo(),2));
             triangle.setHypotenuse(Utils.roundAvoid(hipotenuse,2));
             triangle.setState(TriangleState.POST_CALC);
             processedTriangles.add(triangle);
        }

        return processedTriangles;
    }

}
