document.addEventListener("DOMContentLoaded", async () => {
  const navContainer = document.getElementById("nav-placeholder");
  if (!navContainer) return;
 
  try {
    console.log("hello")
    const response = await import("nav.html");
    const navHtml = await response.text();
    navContainer.innerHTML = navHtml;
  } catch (error) {
    console.error("Could not load navigation:", error);
  }
});