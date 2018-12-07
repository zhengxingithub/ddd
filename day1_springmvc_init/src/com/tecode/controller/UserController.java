package com.tecode.controller;

import com.tecode.model.Message;
import com.tecode.model.User;
import com.tecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/11/9.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        User user1 = userService.login(user.getUsername(),user.getPassword());
        if(user1 != null){
            mv.setViewName("forward:/index.jsp");
            request.getSession().setAttribute("user",user1);
            return mv;
        }
      mv.setViewName("error");
      mv.addObject("登录失败");
      return mv;
    }

    /**
     * 注销方法
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/index";
    }



    @RequestMapping("/toReg")
    public String toReg(){
        return "register";
    }

    @RequestMapping("/check")
    @ResponseBody
    public Message check(String username){
        Message message = new Message();
        message.setStatus(true);
        if(userService.check(username)){
            message.setStatus(false);
            message.setMsg("该用户已经存在了");
        }
        return message;
    }

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public String reg(User user){
        userService.addUser(user);
        return "redirect:/user/toLogin";
    }


    @RequestMapping("/upload")
    public String upload(MultipartFile avatarFile,HttpSession session) throws IOException {
        String fileName = avatarFile.getOriginalFilename();
        System.out.println(fileName);
        String savePath = session.getServletContext().getRealPath("/images/faces");
        File file = new File(savePath,fileName);
        avatarFile.transferTo(file);

        User user = (User)session.getAttribute("user");
        user.setAvatar(fileName);
        userService.updateUserById(user);

        return "redirect:/index";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> export(HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");

        File file=new File(session.getServletContext().getRealPath("/images/faces"),user.getAvatar());
        if(!file.isFile()){
            return null;
        }
        @SuppressWarnings("resource")
        InputStream input=new FileInputStream(file);
        byte[] buff=new byte[input.available()]; // 获取文件大小
        input.read(buff) ;
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename="+user.getAvatar());
        HttpStatus status=HttpStatus.OK;
        ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>(buff,headers,status);
        return  entity;
    }
}
