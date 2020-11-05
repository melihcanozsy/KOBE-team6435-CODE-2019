/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser; f
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends TimedRobot {
  PWMVictorSPX sag1 = new PWMVictorSPX(0);
  PWMVictorSPX sag2 = new PWMVictorSPX(1);
  SpeedControllerGroup sag = new SpeedControllerGroup(sag1, sag2);
  
  PWMVictorSPX sol1 = new PWMVictorSPX(2);
  PWMVictorSPX sol2 = new PWMVictorSPX(3);
  SpeedControllerGroup sol = new SpeedControllerGroup(sol1, sol2);
//////////////////////////////////////
  Spark atma1 = new Spark(8);
  Spark atma2 = new Spark(9);
/////////////////////////////////////
  Spark cekme = new Spark(4);
/////////////////////////////////////
  VictorSP tirmanma = new VictorSP(5);
/////////////////////////////////////
  VictorSP cark = new VictorSP(6);
  ////////////////////////////////////
  DifferentialDrive tekerler
  = new DifferentialDrive(sag,sol);
  /////////////////////////////////////
   Joystick melih = new Joystick(0);
   Joystick atakan = new Joystick(1);
  ////////////////////////////////////// 
   Timer m_timer = new Timer(); 
   private static final String kDefaultAuto = "bos";
   private static final String kCustomAuto = "top";
   private String m_autoSelected;
   private final SendableChooser<String> m_chooser = new SendableChooser<>();

  

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("bos", kDefaultAuto);
    m_chooser.addOption("top", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    CameraServer.getInstance().startAutomaticCapture();
    
  }

  public void robotPeriodic() {
  }

  
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  
  @Override
  public void autonomousPeriodic() {
   if (m_timer.get() > 0.0 && m_timer.get() < 8.0)
   {
     cekme.set(1);
     atma1.set(-1);
     atma2.set(1);
   }
   else if (m_timer.get() > 9.0 && m_timer.get() < 9.6)
   {
    tekerler.arcadeDrive(0.0, -0.5);
   }
   else if (m_timer.get() > 9.6 && m_timer.get() < 11)
   {
    tekerler.arcadeDrive(0.7, 0.0);
   }
   else
    {
      tekerler.arcadeDrive(0.0, 0.0);
      cekme.set(0);
      atma1.set(0);
      atma2.set(0);
    }
   }

  
  @Override
  public void teleopPeriodic() {
    tekerler.arcadeDrive(melih.getY(), melih.getX());
////////////////////////////////////////////////////////
  if (melih.getRawButton(6))
{
  tirmanma.set(1);
}
  else if (melih.getRawButton(5))
{
  tirmanma.set(-0.5);
}
  else
 {
   tirmanma.set(0);
 }
 ////////////////////////////////
 if (atakan.getRawButton(5))
      {
        atma1.set(-1);
        atma2.set(1);
      }
      if (atakan.getRawButton(2))
      {
        atma1.set(0.6);
        atma2.set(-0.6);
      }
      if (!atakan.getRawButton(5) && !atakan.getRawButton(2))
      {
        atma1.set(0);
        atma2.set(0);
      }
  //////////////////////////////////////
  if (atakan.getRawButton(6))
      {
        cekme.set(1);
      }
      if (atakan.getRawButton(1))
      {
        cekme.set(-1);
      }
      if (!atakan.getRawButton(6) && !atakan.getRawButton(1))
      {
        cekme.set(0);
      }
////////////////////////////////////////////
  }

  
  
  @Override
  public void testPeriodic() {
  }
}
