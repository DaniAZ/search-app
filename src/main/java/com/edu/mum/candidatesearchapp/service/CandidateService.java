package com.edu.mum.candidatesearchapp.service;

import com.edu.mum.candidatesearchapp.domain.Candidate;
import com.edu.mum.candidatesearchapp.domain.CandidateList;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateService {

    List<Candidate> cadidateDB = new ArrayList<>();

    /*
        Key:(String) - name of the skill
        Value: (List<Integer>) - index list of candidates who have the skill (key) who are in cadidateDB
     */
    Hashtable<String, List<Integer>> skillsDB = new Hashtable<>();

    int candidateDBIndex = 0; // used to track index of each newly inserted candidate


    public void addCandidate(Candidate candidate){
        synchronized (CandidateService.class){
            cadidateDB.add(candidate);
            for(int i = 0; i < candidate.getSkills().length; i++){
                System.out.println(candidate.getSkills()[i]);
                if(skillsDB.containsKey(candidate.getSkills()[i]))
                    skillsDB.get(candidate.getSkills()[i]).add(candidateDBIndex);
                else
                    skillsDB.put(candidate.getSkills()[i], new ArrayList<>(Arrays.asList(candidateDBIndex)));
            }

            ++candidateDBIndex;
        }

        System.out.println("index is " + candidateDBIndex);
        printCandidateDB();
    }


    public CandidateList searchCandidate(String[] skills){

        /*
            Key: candidate index in the candidateDB list
            Value: skillHits a candidate got
         */
        HashMap<Integer, Integer> skillResult = new HashMap<>();

        for(int i = 0; i < skills.length; i++){

            if(!skillsDB.containsKey(skills[i])) continue;

            List<Integer> candidatesWithSkill_X = skillsDB.get(skills[i]);

            for(int k = 0; k < candidatesWithSkill_X.size(); k++){
                skillResult.put(candidatesWithSkill_X.get(k), i + 1);
                System.out.println("candidates with skill " + skills[i] + cadidateDB.get(candidatesWithSkill_X.get(k)));
            }
        }

        return selectAndSortCandidates(skillResult, skills);
    }


    private CandidateList selectAndSortCandidates(HashMap<Integer, Integer> map, String[] skills){
        List<Candidate> selectedCandidates = new ArrayList<>();
        CandidateList candidateList = new CandidateList();
        candidateList.setSkills(skills);

        for(Integer key : map.keySet()){
            if(map.get(key) == 3)
                selectedCandidates.add(cadidateDB.get(key));
        }

        Collections.sort(selectedCandidates);
        candidateList.setCandidates(selectedCandidates);
        return candidateList;
    }


    private void printCandidateDB(){
        cadidateDB.parallelStream()
                .forEach(x -> System.out.println(x));
        for(String key : skillsDB.keySet()){

            skillsDB.get(key).parallelStream().forEach(x -> System.out.println("skill " + key + " candi: " + x));
        }
    }
}
