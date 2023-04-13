package wascoot.database;

import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopLocation {

    private static final String STATEMENT = "select scooterrack_collection , count(scooterrack_collection) as ct from rental group by scooterrack_collection order by ct desc";

    private final Connection con;

    public TopLocation(Connection con) {
        this.con = con;
    }


    public List<Integer> getTopLocation() throws SQLException  {



        PreparedStatement pstmt = null;
        ResultSet rs = null;


        final List<Integer> topLocations = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                topLocations.add(rs.getInt("scooterrack_collection"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return topLocations;
    }



}
