# What you need #

You need to download Eclipse (http://www.eclipse.org) (pref. Eclipse 3.3) and install a SVN plugin.

If you use Subclipse for example, you have to do the following in Eclipse:

  * Select Help > Software Updates > Find and Install. Select Search for new features to install. Click Next.
  * Click the New Remote Site button. Give the update site a name, like Subclipse, and type in the following address: http://subclipse.tigris.org/update/. Click OK. A new update site will be added to the list.
  * Press the + next to your Subclipse update site. Eclipse will connect with the site and list all of the updates available. Select the most recent Subclipse update by checking the box next to the update. Click Next.
  * Check the Subversion feature that you want to install. Click Next.
  * Accept the terms of the licence agreement. Click Next.
  * Make sure that the Subversion feature is selected to install. Click Finish.
  * You will be asked to verify the feature that you wish to install. After you have, click Install.
  * You will now be asked to restart the workbench. Click Yes.

# How to check out the Project #

After installing Subclipse, open Eclipse, close any open project and do the following in the menu:

File -> New -> Project -> SVN -> Checkout projects from SVN

Select "Create a New Repository Location" and enter:

https://db4o-om.googlecode.com/svn/trunk/

If you're ever asked for a password you should get it here:

http://code.google.com/hosting/settings