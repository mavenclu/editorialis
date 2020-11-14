//package cz.cvut.fel.ear.semestralka.web.controller;
//
//import cz.cvut.fel.ear.semestralka.domain.ManuscriptEvents;
//import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
//import cz.cvut.fel.ear.semestralka.domain.Manuscript;
//import cz.cvut.fel.ear.semestralka.domain.ManuscriptStates;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/manuscripts")
//public class StateMachineController {
//    @Autowired
//    private StateMachine<ManuscriptStates, ManuscriptEvents> stateMachine;
//    @Autowired
//    private ManuscriptRepository manuscriptRepository;
//
//    @RequestMapping("{id}/event/assign-editor")
//    @ResponseBody
//    public String sendFileUploadEvent(@PathVariable Long id){
//        Manuscript man = manuscriptRepository.findByDocumentId(id);
//        System.out.println("man status before event: " + man.getManuscriptStatus());
//        stateMachine.sendEvent(ManuscriptEvents.manuscriptAssignedToEditor);
//        System.out.println("man status after event: " + man.getManuscriptStatus());
//        return man.getManuscriptStatus().label;
//    }
//}
